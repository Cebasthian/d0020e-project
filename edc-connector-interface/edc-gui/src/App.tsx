import { BrowserRouter, Navigate, Route, Routes } from "react-router-dom";
import "./App.css";
import Layout from "./Layout";
import Connectors from "./pages/connectors/Connectors";
import Catalog from "./pages/consuming/catalog/Catalog";
import NegotiatedContracts from "./pages/consuming/negotiated/NegotiatedContracts";
import Transfers from "./pages/consuming/transfers/Transfers";
import MyAssets from "./pages/providing/assets/MyAssets";
import MyContracts from "./pages/providing/contracts/MyContracts";
import MyPolicies from "./pages/providing/policies/MyPolicies";

function App() {

    return (
        <>
        {/* <BrowserRouter> */}
        <BrowserRouter basename="/edc/">
            <Routes>
                <Route path="/" element={<Navigate to="/connectors" replace/>}/>
                <Route element={<Layout/>}>
                    {/* <Route path="/dashboard" element={<Dashboard/>} /> */}
                    <Route path="/connectors" element={<Connectors/>} />
                    {/* Providing */}
                    <Route path="/assets" element={<MyAssets/>} />
                    <Route path="/policies" element={<MyPolicies/>} />
                    <Route path="/contracts" element={<MyContracts/>} />
                    {/* Consuming */}
                    <Route path="/catalog" element={<Catalog/>} />
                    <Route path="/negotiated-contracts" element={<NegotiatedContracts/>} />
                    <Route path="/transfers" element={<Transfers/>} />
                </Route>
                <Route path="/*" element={<Navigate to="/"/>} />
            </Routes>
        </BrowserRouter>
        </>
    );
}

export default App;
