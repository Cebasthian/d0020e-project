import { useEffect, useState } from "react";
import Attribute from "../../components/field/Attribute";
import Fields from "../../components/field/Fields";
import SubLayout from "../../components/layout/SubLayout";
import { EdcMetadata } from "../../types/edc";
import { getConnectors } from "../../util/edc_interface";
import styles from "./connectors.module.css";

export default function Connectors() {

    const [connectors, setConnectors] = useState<EdcMetadata[]>([])

    useEffect(() => {

        async function func() {
            const json = await getConnectors();
            setConnectors(json)
        }
        func()

    }, [])

    return(
        <>
        <SubLayout title="Trusted Connectors">
            <div className={styles.container}>
                {connectors.map(e => <ConnectorComponent metadata={e} key={e.participantId}/>)}
            </div>
        </SubLayout>
        
        </>
    )
}

function ConnectorComponent({
    metadata
}: {metadata: EdcMetadata}) {
    return(
        <>
        <div className={"card " + styles.connector}>
            <h3>{metadata.name}</h3>
            <p>{metadata.description}</p>
            <Fields>
                <Attribute icon="link" text="ENDPOINT" value={(<a href={metadata.protocolAddress}>{metadata.protocolAddress}</a>)} />
                <Attribute icon="id_card" text="PARTICIPANT ID" value={metadata.participantId} />
            </Fields>
        </div>
        </>
    )
}