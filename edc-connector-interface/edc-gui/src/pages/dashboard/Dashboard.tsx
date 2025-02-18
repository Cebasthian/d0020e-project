import SubLayout from "../../components/layout/SubLayout";
import styles from "./dashboard.module.css";

export default function Dashboard() {



    return(
        <>
        <SubLayout title="Dashboard">
            
            <div className={styles.container}>
                <div className="card">
                    <h2>todo</h2>
                </div>
            </div>


        </SubLayout>
        </>
    )
}