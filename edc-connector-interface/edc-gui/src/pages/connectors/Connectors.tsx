import { useEffect, useState } from "react";
import Icon from "../../components/icon/Icon";
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
            <div className={styles.fields}>
                <div className={styles.attribute}>
                    <Icon>link</Icon>
                    <div>
                        <span>ENDPOINT</span>
                        <span>
                            <a href={metadata.protocolAddress}>{metadata.protocolAddress}</a>
                        </span>
                    </div>
                </div>
                <div className={styles.attribute}>
                    <Icon>id_card</Icon>
                    <div>
                        <span>PARTICIPANT ID</span>
                        <span>{metadata.participantId}</span>
                    </div>
                </div>
            </div>
        </div>
        </>
    )
}