import Icon from "../icon/Icon"
import styles from "./fields.module.css"

type AttributeProps = {
    icon: string,
    text: React.ReactNode,
    value: React.ReactNode
}

export default function Attribute({
    icon,
    text,
    value
}:AttributeProps) {
    return(
        <div className={styles.attribute}>
            <Icon>{icon}</Icon>
            <div>
                <span>{text}</span>
                <span>{value}</span>
            </div>
        </div>
    )
}