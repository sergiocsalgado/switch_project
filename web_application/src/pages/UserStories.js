import React, {useContext, useEffect, useState} from "react";

import FormUserStory from "../components/product-backlog/FormUserStory";

import TableUS from "../components/product-backlog/TableUS";
import FooterTables from "../components/utils/FooterTables";
import {fetchUserStoryFromProject} from "../context/Actions";
import AppContext from "../context/AppContext";
import {useParams} from "react-router";

const UserStories = () => {
    const { dispatch } = useContext(AppContext);
    const ButtonBack = () => {
        window.history.back();
    };
    const {projectCode} = useParams();
    useEffect(() => {
        if (projectCode) {
            fetchUserStoryFromProject(projectCode, dispatch);
        }
    }, [projectCode, dispatch]);


    return (
        <div className="proj-container">
            <h1 style={{ marginLeft: '20px' }}>User Stories</h1>
            <div>
                <TableUS projectCode={projectCode} />
            </div>
            <div>
                <FormUserStory />
                <button className="back-button" onClick={ButtonBack}>Go Back</button>
            </div>
            <FooterTables/>
        </div>
    );
}



export default UserStories;