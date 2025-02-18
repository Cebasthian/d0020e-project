import styles from "./fields.module.css"

export default function Fields({children}: {children: React.ReactNode}) {
    return(
        <div className={styles.fields}>{children}</div>
    )
}