import { Outlet } from "react-router-dom";
import Sidebar from "./components/sidebar/Sidebar";

export default function Layout() {
    return(
        <>
        <div className="layout">
            <Sidebar/>
            <main>
                <Outlet/>
            </main>
        </div>
        </>
    )
}