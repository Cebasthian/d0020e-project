import { useCallback, useEffect, useState } from "react";
import Attribute from "../../../components/field/Attribute";
import Fields from "../../../components/field/Fields";
import Icon from "../../../components/icon/Icon";
import SubLayout from "../../../components/layout/SubLayout";
import { OdrlHasPolicy } from "../../../types/odrl";
import { getCatalog, getConnectors, negotiateContract } from "../../../util/edc_interface";
import { jsonLd } from "../../../util/json_ld";
import styles from "./catalog.module.css";

type AssetItem = {
    participantId: string,
    connectorAddress: string,
    name: string,
    id: string,
    policy: OdrlHasPolicy
}

export default function Catalog() {

    const [assets, setAssets] = useState<AssetItem[]>([])

    useEffect(() => {

        async function func() {
            const connectors = await getConnectors();
            const arr: AssetItem[] = []
            for(let i = 0; i < connectors.length; i++) {
                const catalog = jsonLd.removeNamespace(await getCatalog(connectors[i].protocolAddress))

                catalog.dataset.forEach(dataset => {
                    arr.push({
                        participantId: catalog.participantId,
                        connectorAddress: connectors[i].protocolAddress,
                        name: dataset.name,
                        id: dataset.id,
                        policy: dataset.hasPolicy,
                    })
                })
            }
            setAssets(arr)
        }
        func()


    }, [])

    return(
        <>
        <SubLayout title="Catalog">
            <div className={styles.container}>
                <div className={styles['catalog-assets']}>
                    {assets.map((e) => <AssetComponent key={e.id} asset={e} />)}
                </div>
            </div>
        </SubLayout>
        </>
    )
}

function AssetComponent({
    asset
}: {asset: AssetItem}) {

    const negotiate = useCallback(async () => {
        const json = await negotiateContract(asset.policy, asset.connectorAddress, asset.participantId, asset.id)
        console.log(json)
    }, [asset])

    return(
        <>
        <div className={"card " + styles.asset}>
            <h3>{asset.name}</h3>
            <span>TODO: Policy Rules</span>
            <Fields>
                <Attribute icon="fingerprint" text="ASSET ID" value={asset.id} />
                <Attribute icon="id_card" text="PARTICIPANT ID" value={asset.participantId} />
            </Fields>
            <div className={styles.negotiate}>
                <button className={styles['negotiate-button']} onClick={negotiate}>
                    <Icon>sync_alt</Icon>
                    Begin Negotiation
                </button>
            </div>
        </div>
        </>
    )
}