import { useCallback, useEffect, useState } from "react";
import Attribute from "../../../components/field/Attribute";
import Fields from "../../../components/field/Fields";
import Icon from "../../../components/icon/Icon";
import SubLayout from "../../../components/layout/SubLayout";
import { EdcAgreeement, EdcNegotiation } from "../../../types/edc";
import { beginTransfer, getAgreement, pollNegotiationState } from "../../../util/edc_interface";
import { jsonLd } from "../../../util/json_ld";
import { useGetter } from "../../../util/useGetter";
import styles from "./negotations.module.css";

export default function NegotiatedContracts() {

    const {data} = useGetter<EdcNegotiation[]>("/edc-consumer/contract/negotiations", [])

    return(
        <>
        <SubLayout title="Negotiated Contracts">
            <div className={styles.container}>
                <div className={styles.negotiations}>
                    {data.map((e) => <ContractComponent key={e["@id"]} negotiation={e} />)}
                </div>
            </div>
        </SubLayout>
        </>
    )
}

function ContractComponent({
    negotiation
}: {negotiation: EdcNegotiation}) {



    const [agreement, setAgreement] = useState<EdcAgreeement | null>(null)
    const [state, setState] = useState<string>(negotiation.state)

    useEffect(() => {

        async function req() {
            if(negotiation.state === "TERMINATED") return;

            let agreementId = negotiation.contractAgreementId || negotiation["@id"];
            if(negotiation.state !== "FINALIZED") {
                const json = await pollNegotiationState(negotiation["@id"], e => setState(e))
                if(json.contractAgreementId) agreementId = json.contractAgreementId;
            }

            const agree = jsonLd.removeNamespace(await getAgreement(agreementId));
            setAgreement(agree)
        }

        req();

    }, [negotiation])

    // TODO:
    // Begin Transfer function
    // If state != finalized : start polling until finalized
    // else : fetch agreement info


    

    // const negotiate = useCallback(async () => {
    //     const json = await negotiateContract(asset.policy, asset.connectorAddress, asset.participantId, asset.id)
    //     console.log(json)
    // }, [asset])

    const transfer = useCallback(async () => {
        if(!agreement) return; 

        const json = await beginTransfer({
            connectorId: negotiation.counterPartyId,
            counterPartyAddress: negotiation.counterPartyAddress,
            contractId: agreement["@id"]
        })
        console.log(json)
    }, [negotiation, agreement])

    return(
        <>
        <div className={"card " + styles.negotiation}>
            <h3>{negotiation.type}</h3>
            <Fields>
                <Attribute icon="id_card" text="PARTICIPANT ID" value={negotiation.counterPartyId} />
                <Attribute icon="fingerprint" text="NEGOTIATION ID" value={negotiation["@id"]} />
            </Fields>        
            <Fields>
                <Attribute icon="monitor_heart" text="STATE" value={state} />
            </Fields>
            <Fields>
                {agreement ? <Attribute icon="verified" text="AGREEMENT ID" value={agreement["@id"]} /> : ""}
                {agreement ? <Attribute icon="calendar_month" text="SIGN DATE" value={new Date((agreement.contractSigningDate || 0)*1000).toISOString()} /> : ""}
            </Fields>
            {
                negotiation.type === "CONSUMER" ? (
                    <div className={styles.transfer}>
                        <button onClick={transfer} disabled={agreement === null} className={styles['transfer-button']}>
                            <Icon>cloud_download</Icon>
                            Transfer
                        </button>
                    </div>
                ) : ""
            }
        </div>
        </>
    )
}