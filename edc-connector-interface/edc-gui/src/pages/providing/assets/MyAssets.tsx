import { useCallback, useMemo, useState } from "react";
import Attribute from "../../../components/field/Attribute";
import Fields from "../../../components/field/Fields";
import Icon from "../../../components/icon/Icon";
import SubLayout from "../../../components/layout/SubLayout";
import Modal from "../../../components/modal/Modal";
import { EdcAsset } from "../../../types/edc";
import { createAsset } from "../../../util/edc_interface";
import { isUrl } from "../../../util/regex";
import { useGetter } from "../../../util/useGetter";
import styles from "./my-assets.module.css";


export default function MyAssets() {
    const {data: assets, refresh} = useGetter<EdcAsset[]>("/edc-provider/assets", [])

    const [showModal, setShowModal] = useState(false);

    const [name, setName] = useState("")
    const [address, setAddress] = useState("")
    const [error, setError] = useState("")

    const show = () => {
        setName("")
        setAddress("")
        setError("")
        setShowModal(true)
    }

    const create = useCallback(async () => {
        
        if(!name) {
            setError("Name is invalid")
            return;
        }

        if(!isUrl(address)) {
            setError("Address is not a valid URL")
            return;
        }

        const data = await createAsset(name, address)
        console.log(data)

        setShowModal(false);
        refresh();
    }, [address, name, refresh])

    const modal = useMemo(() => {
        return (
            <Modal setShow={setShowModal}>
                <div className={styles.modal}>
                    <h3>Create Asset</h3>
                    <span className={styles['modal-error']}>{error}</span>
                    <label>
                        <span>Asset Name</span>
                        <input required placeholder="Name *" value={name} onChange={e => setName(e.target.value)} />
                    </label>
                    <label>
                        <span>Data Address</span>
                        <input required placeholder="URL *" value={address} onChange={e => setAddress(e.target.value)} />
                    </label>
                    <div className={styles['modal-buttons']}>
                        <button onClick={() => setShowModal(false)}>Cancel</button>
                        <button onClick={() => create()}>Create</button>
                    </div>
                </div>
            </Modal>
        );
    }, [name, address, error, create]);

    return (
        <>
            <SubLayout title="My Assets">
                <div className={styles.container}>
                    <div className={styles["create-asset"]}>
                        <button
                            className={styles["create-asset-button"]}
                            onClick={show}
                        >
                            <Icon>add_circle</Icon> Create New Asset
                        </button>
                    </div>
                    <div className={styles['my-assets']}>
                        {assets.map((e) => <AssetComponent key={e["@id"]} asset={e} />)}
                    </div>
                </div>
            </SubLayout>
            {showModal ? modal : ""}
        </>
    );
}

type AssetProps = {
    asset: EdcAsset
}

function AssetComponent({
    asset
}: AssetProps) {
    return(
        <>
        <div className={"card " + styles.asset}>
            <h3>{asset.properties.name}</h3>
            <Fields>
                <Attribute icon="download" text="DATA ADDRESS" value={<a href={asset.dataAddress.baseUrl}>{asset.dataAddress.baseUrl}</a>} />
                <Attribute icon="fingerprint" text="ASSET ID" value={asset["@id"]} />
            </Fields>
        </div>
        </>
    )
}