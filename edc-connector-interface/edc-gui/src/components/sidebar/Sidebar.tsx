import { NavLink } from "react-router-dom";
import Icon from "../icon/Icon";
import styles from "./sidebar.module.css";

export default function Sidebar() {
    return(
        <>
        <div className={styles.sidebar}>
            <div className={styles.logo}>
                <img src="logo.png"/>
            </div>

            <NavButton icon="token" href="/dashboard">Dashboard</NavButton>
            <NavButton icon="hub" href="/connectors">Connectors</NavButton>

            <div className={styles.group}>
                <span className={styles['group-label']}>PROVIDING</span>
                <NavButton icon="upload" href="/assets">Assets</NavButton>
                <NavButton icon="policy" href="/policies">Policies</NavButton>
                <NavButton icon="contract" href="/contracts">Contracts</NavButton>
            </div>

            <div className={styles.group}>
                <span className={styles['group-label']}>CONSUMING</span>
                <NavButton icon="web" href="/catalog">Catalog</NavButton>
                <NavButton icon="sdk" href="/negotiated-contracts">Negotiated Contracts</NavButton>
                <NavButton icon="route" href="/transfers">Transfers</NavButton>
            </div>
        </div>
        </>
    )
}

type NavButtonProps = {
    children: React.ReactNode
    icon: string,
    href: string
}

function NavButton({
    children,
    icon,
    href
}: NavButtonProps) {
    return(
        <>
        <NavLink to={href} className={({isActive}) => {
            const classList: string[] = [styles['nav-button']]

            if(isActive) classList.push(styles['nav-button-active'])

            return classList.join(" ");
        }}>
            <Icon>{icon}</Icon>
            <span>{children}</span>
        </NavLink>
        </>
    )
}