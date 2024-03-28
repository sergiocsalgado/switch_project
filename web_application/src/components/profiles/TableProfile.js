import React, {useContext, useEffect, useState} from "react";
import TableHeaderProfile from "./TableHeaderProfile";
import TableBodyProfile from "./TableBodyProfile";
import AppContext from "../../context/AppContext";
import {fetchProfilesAction} from "../../context/Actions";

function TableProfile() {
    const {state, dispatch} = useContext(AppContext);
    const {profiles} = state;
    const {loading, error, data} = profiles;

    useEffect(() => {
        fetchProfilesAction(dispatch);
    }, [dispatch]);

    const [tableData, setTableData] = useState([]);

    useEffect(() => {
        if (data.length > 0) {
            setTableData([...data]);
        }
    }, [data]);


    if (loading === true) {
        return (<h1>Loading ....</h1>);
    } else {
        if (error !== null) {
            return (<h1>Error ....</h1>);
        } else {
            return (
                <div>
                    {tableData.length > 0 ? (
                        <table border="1">
                            <TableHeaderProfile/>
                            <TableBodyProfile data={tableData}/>
                        </table>
                    ) : (
                        <h1>No data ....</h1>
                    )}
                </div>
            );
        }
    }

}

export default TableProfile;