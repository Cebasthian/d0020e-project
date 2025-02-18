import Attribute from "../../../components/field/Attribute";
import Fields from "../../../components/field/Fields";
import Icon from "../../../components/icon/Icon";
import SubLayout from "../../../components/layout/SubLayout";
import { EdcNegotiation } from "../../../types/edc";
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

    // TODO:
    // Begin Transfer function
    // If state != finalized : start polling until finalized
    // else : fetch agreement info
    

    // const negotiate = useCallback(async () => {
    //     const json = await negotiateContract(asset.policy, asset.connectorAddress, asset.participantId, asset.id)
    //     console.log(json)
    // }, [asset])

    return(
        <>
        <div className={"card " + styles.negotiation}>
            <h3>{negotiation["@id"]}</h3>
            <Fields>
                <Attribute icon="id_card" text="PARTICIPANT ID" value={negotiation.counterPartyId} />
                <Attribute icon="fingerprint" text="NEGOTIATION ID" value={negotiation["@id"]} />
            </Fields>        
            <Fields>
                <Attribute icon="monitor_heart" text="STATE" value={negotiation.state} />
                {negotiation.contractAgreementId? <Attribute icon="verified" text="AGREEMENT ID" value={negotiation.contractAgreementId} /> : ""}
            </Fields>
            <div className={styles.negotiate}>
                <button className={styles['negotiate-button']}>
                    <Icon>sync_alt</Icon>
                    Transfer
                </button>
            </div>
        </div>
        </>
    )
}