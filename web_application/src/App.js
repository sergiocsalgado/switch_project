import {useContext} from "react";
import { Helmet } from "react-helmet";
import "./App.css";
import Nav from "./components/utils/Nav";
import Route from "./routes/Route";
import AppContext from "./context/AppContext";
import FooterGlobal from "./components/utils/FooterGlobal";
import HeaderGlobal from "./components/utils/HeaderGlobal";

function App() {
    const {state} = useContext(AppContext);
    const {nav} = state;
    const {menus} = nav;

    return (
        <div className="App">
            <Helmet>
                <meta name="viewport" content="width=device-width, initial-scale=1.0" />
            </Helmet>

            <HeaderGlobal />
            <Nav items={menus} />
            <Route />
            <FooterGlobal />
        </div>
    );
}

export default App;
