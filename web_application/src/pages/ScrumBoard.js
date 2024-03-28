import React, {useContext, useEffect} from "react";

import FooterTables from "../components/utils/FooterTables";
import {fetchScrumoardAction} from "../context/Actions";
import AppContext from "../context/AppContext";
import {useParams} from "react-router";
import TableScrumBoard from "../components/scrumBoard/TableScrumBoard";
import '../App.css';

const SprintBacklog = () => {
    const { dispatch } = useContext(AppContext);
    const ButtonBack = () => {
        window.history.back();
    };
    const {projectCode} = useParams();
    useEffect(() => {
        if (projectCode) {
            fetchScrumoardAction(projectCode, dispatch);
        }
    }, [projectCode, dispatch]);


    return (
        <div className= "proj-container">
            <h1 style={{ marginLeft: '20px' }}>Scrum Board</h1>
            <div>
                <TableScrumBoard projectCode={projectCode} />
            </div>
            <div>
                <button className="back-button-SB" onClick={ButtonBack}>Go Back</button>
            </div>
            <FooterTables/>
        </div>
    );
}

export default SprintBacklog;