import styles from "./modal.module.css";

type ModalProps = {
    children: React.ReactNode,
    setShow: (show: boolean) => void,
}

export default function Modal({
    children,
    setShow
}: ModalProps) {

    const outer = (e: React.MouseEvent<HTMLDivElement, MouseEvent>) => {
        e.stopPropagation();
        setShow(false)
    }
    const inner = (e: React.MouseEvent<HTMLDivElement, MouseEvent>) => {
        e.stopPropagation();
    }

    return(
        <>
        <div className={styles.modal} onClick={outer}>
            <div className={styles.inner} onClick={inner}>
                {children}
            </div>
        </div>
        </>
    )
}