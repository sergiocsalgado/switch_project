import React, {useContext, useEffect} from "react";

import FormUserStoryInSprint from "../components/sprintBacklog/FormUserStoryInSprint";

import FooterTables from "../components/utils/FooterTables";
import {fetchUserStoryInSprint} from "../context/Actions";
import AppContext from "../context/AppContext";
import {useParams} from "react-router";
import TableUSInSprint from "../components/sprintBacklog/TableUSInSprint";

const SprintBacklog = () => {
    const { dispatch } = useContext(AppContext);
    const ButtonBack = () => {
        window.history.back();
    };
    const {projectCode,sprintID} = useParams();
    useEffect(() => {
        if (sprintID) {
            fetchUserStoryInSprint(projectCode,sprintID, dispatch);
        }
    }, [projectCode,sprintID, dispatch]);

    return (
        <div>
            <h1 style={{ marginLeft: '20px' }}>User Stories</h1>
            <div>
                <TableUSInSprint projectCode={projectCode} sprintID={sprintID} />
            </div>
            <div>
                <FormUserStoryInSprint projectCode={projectCode} sprintID={sprintID}/>
                <button className="back-button-USInSprint" onClick={ButtonBack}>Go Back</button>
            </div>
            <FooterTables/>
        </div>
    );
}



export default SprintBacklog;