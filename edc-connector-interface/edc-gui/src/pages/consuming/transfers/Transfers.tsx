import { useCallback, useEffect, useState } from "react";
import JSONPretty from "react-json-pretty";
import 'react-json-pretty/themes/monikai.css';
import Attribute from "../../../components/field/Attribute";
import Fields from "../../../components/field/Fields";
import Icon from "../../../components/icon/Icon";
import SubLayout from "../../../components/layout/SubLayout";
import { EdcTransfer } from "../../../types/edc";
import { pollTransferState, retrieveData } from "../../../util/edc_interface";
import { useGetter } from "../../../util/useGetter";
import styles from "./transfers.module.css";

export default function Transfers() {

    
    const {data} = useGetter<EdcTransfer[]>("/edc-consumer/transfer", [])

    return(
        <>
        <SubLayout title="Transfers">
            <div className={styles.container}>
                <div className={styles.transfers}>
                    {data.map((e) => <TransferComponent key={e["@id"]} transfer={e} />)}
                </div>
            </div>
        </SubLayout>
        </>
    )
}

function TransferComponent({
    transfer
}: {transfer: EdcTransfer}) {

    const [state, setState] = useState<"REQUESTED" | "STARTED" | "SUSPENDED" | "RESUMED" | "COMPLETED" | "TERMINATED">(transfer.state)

    // eslint-disable-next-line @typescript-eslint/no-explicit-any
    const [data, setData] = useState<any|null>(null)

    useEffect(() => {
        async function req() {
            if(transfer.state === "TERMINATED") return;

            if(transfer.state === "REQUESTED") {
                await pollTransferState(transfer["@id"], e => setState(e.state))
            }
        }

        req();

    }, [transfer])

    const retrieve = useCallback(async () => {
        if(state !== "STARTED") {
            return;
        }

        const json = await retrieveData(transfer["@id"])
        setData(json)

    }, [transfer, state])

    return(
        <>
        <div className={"card " + styles.transfer}>
            <h3>{transfer.type}</h3>
            <Fields>
                <Attribute icon="calendar_month" text="TRANSFER DATE" value={new Date((transfer.stateTimestamp || 0)).toISOString()} />
                <Attribute icon="fingerprint" text="TRANSFER ID" value={transfer["@id"]} />
            </Fields>        
            <Fields>
                <Attribute icon="monitor_heart" text="STATE" value={state} />
                <Attribute icon="fingerprint" text="ASSET ID" value={transfer.assetId} />
            </Fields>
            {
                transfer.type === "CONSUMER" ? (
                    <div className={styles.retrieve}>
                        <button onClick={retrieve} className={styles['retrieve-button']}>
                            <Icon>download</Icon>
                            Retrieve Data
                        </button>
                    </div>
                ) : ""
            }
            
            {data !== null ? <JSONPretty data={data} /> : ""}
        </div>
        </>
    )
}