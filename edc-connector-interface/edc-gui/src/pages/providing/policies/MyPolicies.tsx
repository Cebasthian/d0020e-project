import SubLayout from "../../../components/layout/SubLayout";
import { EdcAsset } from "../../../types/edc";
import { useGetter } from "../../../util/useGetter";
import styles from "./my-policies.module.css";

export default function MyPolicies() {
    const {data: policies, refresh} = useGetter<EdcAsset[]>("/edc-provider/policies", [])



    return(
        <>
        <SubLayout title="My Policies">
            <div className={styles.container}>
                <pre>{JSON.stringify(policies, null, 4)}</pre>
            </div>
        </SubLayout>
        </>
    )
}