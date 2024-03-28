import React, {useReducer} from 'react';
import PropTypes from "prop-types";
import {Provider} from './AppContext';
import reducer from './Reducer';

const menus = [
    {key: 'home', label: 'Home'},
    {key: 'projects', label: 'Projects'},
    {key: 'about', label: 'About'},
]

const nav = {
    selectedMenu: menus[0],
    menus: menus,
};

const headersProjects = {
    projectCode: "Project Code",
    name: "Name",
    description: "Description",
    customer: "Customer",
    businessSector: "Business Sector",
    typology: "Typology",
    startDate: "Start Date",
    endDate: "End Date",
    status: "Status",
    sprintDuration: "Sprint Duration",
    numberOfPlannedSprints: "Planned Sprints",
    budget: "Budget"
}

const headersSprint = {
    projectCode: "Project",
    sprintID: "ID",
    sprintNumber: "Number",
    startDate: "Start Date",
    endDate: "End Date",
    sprintStatus: "Sprint Status"
};

const headersHumanResource = {
    projectCode: "Project",
    resourceInProjectID: "ID",
    email: "Email",
    role: "Role",
    allocation: "Allocation",
    costPerHour: "Cost per Hour",
    currency: "Currency",
    startDate: "Start Date",
    endDate: "End Date"
};

const headersProfile = {
    profileID: "ID",
    description: "Description",
};

const headersUS = {
    projectCode: "Project Code",
    userStoryID: "US ID",
    userStoryText: "Description",
    status: "Status",
    userStoryNumber: "US Number",
    actor: "Actor",
    priority: "Priority",
}
const initialState = {
    nav,
    headersUS,
    userStories: {
        projectCode: "",
        loading: true,
        error: null,
        data: [],
    },
    headersProfile,
    profiles: {
        loading: true,
        error: null,
        data: [],
    },
    headersProjects,
    projects: {
        loading: true,
        error: null,
        data: [],
    },
    headersSprint,
    sprints: {
        projectCode: "",
        loading: true,
        error: null,
        data: [],
    },
    headersHumanResource,
    resources: {
        projectCode: "",
        loading: true,
        error: null,
        data: [],
    },
}
/**
 Wraps the application with a context provider that provides the application state and a dispatch function.

 @param props - An object containing the properties of the component.
 @param props.children - The child components that will be rendered within the context provider.
 @returns The context provider with the application state and a dispatch function.
 */

const AppProvider = (props) => {

    const [state, dispatch] = useReducer(reducer, initialState);
    return (
        <Provider value={{
            state,
            dispatch,
        }}>
            {props.children}
        </Provider>
    );
};

AppProvider.propTypes = {
    children: PropTypes.node,
};

export default AppProvider;
