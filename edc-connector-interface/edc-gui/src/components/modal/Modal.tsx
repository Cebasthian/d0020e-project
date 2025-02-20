import { useCallback, useEffect, useState } from "react";
import styles from "./modal.module.css";

type ModalProps = {
    children: React.ReactNode;
    setShow: (show: boolean) => void;
};

export default function Modal({ children, setShow }: ModalProps) {
    const [mouseDownOutside, setMouseDownOutside] = useState(false);

    const handleMouseUp = useCallback(() => {
        if (mouseDownOutside) {
            setShow(false);
        }
        setMouseDownOutside(false);
    }, [mouseDownOutside, setShow, setMouseDownOutside])

    useEffect(() => {
        document.addEventListener("mouseup", handleMouseUp);
        return () => {
            document.removeEventListener("mouseup", handleMouseUp);
        };
    }, [mouseDownOutside, handleMouseUp]);

    return (
        <>
            <div className={styles.modal} onMouseDown={(e) => {
                e.stopPropagation();
                setMouseDownOutside(true);
            }}>
                <div
                    className={styles.inner}
                    onMouseDown={(e) => {
                        e.stopPropagation();
                        setMouseDownOutside(false);
                    }}
                >
                    {children}
                </div>
            </div>
        </>
    );
}
