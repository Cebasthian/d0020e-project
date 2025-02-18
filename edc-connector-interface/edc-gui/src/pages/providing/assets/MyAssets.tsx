import { useMemo, useState } from "react";
import Icon from "../../../components/icon/Icon";
import SubLayout from "../../../components/layout/SubLayout";
import Modal from "../../../components/modal/Modal";
import { EdcAsset } from "../../../types/edc";
import { createAsset } from "../../../util/edc_interface";
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

    const create = async () => {
        
        if(!name) {
            setError("Name is invalid")
            return;
        }

        const http_regex = /^https?:\/\//g
        if(!http_regex.test(address)) {
            setError("Address is not a valid URL")
            return;
        }

        const data = await createAsset(name, address)
        console.log(data)

        setShowModal(false);
        refresh();
    }

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
    }, [name, address, error]);

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
                    {assets?.map((e) => (
                        <div key={e["@id"]} className="card">
                            <pre>{JSON.stringify(e, null, 4)}</pre>
                        </div>
                    ))}
                </div>
            </SubLayout>
            {showModal ? modal : ""}
        </>
    );
}
