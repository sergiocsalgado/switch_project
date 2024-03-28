import React, { useContext, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import AppContext from '../context/AppContext';
import FormHumanResources from '../components/humanResources/FormHumanResources';
import FooterTables from '../components/utils/FooterTables';
import TableResources from '../components/humanResources/TableResources';
import { fetchResourcesFormProject } from '../context/Actions';
import '../App.css';

const HumanResources = () => {
    const { dispatch } = useContext(AppContext);
    const ButtonBack = () => {
        window.history.back();
    };

    const { projectCode } = useParams();

    useEffect(() => {
        if (projectCode) {
            fetchResourcesFormProject(projectCode, dispatch);
        }
    }, [projectCode, dispatch]);

    return (
        <div className="proj-container">

            <h1 style={{ marginLeft: '20px' }}>Project Team</h1>

            <div>
                <TableResources projectCode={projectCode} />
            </div>
            <div>
                <FormHumanResources/>
            </div>
            <button className="back-button" onClick={ButtonBack}>Go Back</button> <FooterTables />
        </div>
    );
}

export default HumanResources;
