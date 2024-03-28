import React, {useContext, useEffect, useState} from "react";

import FormSprint from "../components/sprints/FormSprint";
import TableSprint from "../components/sprints/TableSprint";
import FooterTables from "../components/utils/FooterTables";
import {fetchSprintsFromProject} from "../context/Actions";
import AppContext from "../context/AppContext";
import {useParams} from "react-router";
import '../App.css';

const Sprints = () => {
    const {dispatch} = useContext(AppContext);
    const ButtonBack = () => {
        window.history.back();
    };
    const {projectCode} = useParams();
    useEffect(() => {
        if (projectCode) {
            fetchSprintsFromProject(projectCode, dispatch);
        }
    }, [projectCode, dispatch]);

    return (
        <div className="proj-container">
            <h1 style={{ marginLeft: '20px' }}>Sprints</h1>
            <div>
                <TableSprint projectCode={projectCode}/>
            </div>
            <div>
                <FormSprint/>
                <button className="back-button" onClick={ButtonBack}>Go Back</button>
            </div>
            <FooterTables/>
        </div>
    );
};

export default Sprints;
