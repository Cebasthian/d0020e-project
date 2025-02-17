import styles from "./sublayout.module.css"

type SubLayoutProps = {
    children: React.ReactNode,
    title: string
}

export default function SubLayout({
    children,
    title
}: SubLayoutProps) {
    return(
        <div className={styles.container}>
            <div className={styles.top}>
                <span>{title}</span>
            </div>
            {children}
        </div>
    )
}