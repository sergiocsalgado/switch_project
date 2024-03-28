import React, {useContext, useEffect, useState} from "react";
import Select from 'react-select';
import AppContext from "../../context/AppContext";
import "../formStyle.css";
import {useParams} from "react-router";
import {createResourcesAction} from "../../context/Actions";
import {v4 as uuidv4} from 'uuid';
import ConfirmationModal from "../ConfirmationModal/ConfirmationModal";
import {showAlertModal} from "../utils/Alert";

function FormHumanResources() {
    // State variables
    const [showForm, setShowForm] = useState(false); // State to toggle form visibility
    const {dispatch, state} = useContext(AppContext);
    const {projects} = state;
    const {projectCode, status} = useParams();
    const actualStatus = status !== "Closed";
    const myUuid = uuidv4().slice(0, 8);
    const [showConfirmationModal, setShowConfirmationModal] = useState(false);
    console.log(myUuid);
    const [newResources, setNewResources] = useState({
        // State to hold new resource data
        projectCode: projectCode,
        resourceInProjectID: myUuid,
        email: "",
        role: "team member", // Setting the default value as "team member"
        allocation: "",
        costPerHour: "",
        startDate: "",
        endDate: "",
        currency: "EUR", // Setting the default currency as "EUR"
    });

    // Event handler for form submission
    const handleSubmit = (event) => {
        event.preventDefault();
        setShowConfirmationModal(true);
    };

    const handleConfirm = () => {
        createResourcesAction(projectCode, dispatch, newResources);
        setNewResources({
            projectCode: projectCode,
            resourceInProjectID: myUuid,
            email: "",
            role: "", // Setting the default value as "team member"
            allocation: "",
            costPerHour: "",
            startDate: "",
            endDate: "",
            currency: "", // Setting the default currency as "EUR"
        });
        setShowForm(false);
        setShowConfirmationModal(false);

        showAlertModal("Success!", "Form submitted successfully!");
    };

    const handleCancel = () => {
        setShowConfirmationModal(false);
    };

    const toggleModal = () => {
        setShowConfirmationModal(!showConfirmationModal);
    };

    const handleShowForm = () => {
        setShowForm(!showForm);
    };

    const handleChange = (event) => {
        const {name, value} = event.target;
        if (name === "currency" && value !== "EUR") {
            // Validating currency input
            alert("Please enter 'EUR' as the currency.");
            return;
        }
        setNewResources((prevNewResources) => ({
            ...prevNewResources,
            [name]: value,
        }));
    };

    const [accounts, setAccounts] = useState([]);

    useEffect(() => {
        const fetchAccounts = async () => {
            try {
                const response = await fetch("http://localhost:8080/accounts");
                const data = await response.json();
                setAccounts(data);
            } catch (error) {
                console.error("Error fetching accounts:", error);
            }
        };

        fetchAccounts();
    }, []);

    const handleAccountChange = (selectedOption) => {
        setNewResources((prevNewResources) => ({
            ...prevNewResources,
            email: selectedOption.value,
        }));
    };

    const accountOptions = accounts.map((account) => ({
        value: account.email,
        label: `${account.name} (${account.email})`,
    }));

    const handleRoleChange = (event) => {
        const value = event.target.value;
        setNewResources((prevNewResources) => ({
            ...prevNewResources,
            role: value,
        }));
    };

    return (
        <div>
            {actualStatus &&
                <button className="button-Add" onClick={handleShowForm}>
                    {showForm ? "Close" : "Add"}
                </button>}
            {showForm && (
                <form className="form" onSubmit={handleSubmit}>
                    <h1>Add Project Team</h1>
                    <p>
                        <label className="projectCode">
                            <input
                                className="projectCodeBox"
                                type="text"
                                id="projectCode"
                                name="projectCode"
                                value={newResources.projectCode}
                                required
                                readOnly
                                hidden
                            />
                        </label>
                    </p>
                    <p>
                        <label className="resourceInProjectID">
                            <input
                                className="resourceInProjectIDBox"
                                type="text"
                                id="resourceInProjectID"
                                name="resourceInProjectID"
                                value={newResources.resourceInProjectID}
                                required
                                readOnly
                                hidden

                            />
                        </label>
                    </p>
                    <p>
                        <label className="boxTitle">
                            Account
                        </label>
                        <Select
                            options={accountOptions}
                            onChange={handleAccountChange}
                            className="select-dropdown"
                        />
                    </p>
                    <p>
                        <label className="boxTitle">
                            Role
                            <select
                                className="dropdownlBox"
                                id="role"
                                name="role"
                                value={newResources.role || ""}
                                onChange={handleRoleChange}
                                required
                            >
                                <option selected disabled value="">Select</option>
                                <option value="team member">Team Member</option>
                                <option value="product owner">Product Owner</option>
                                <option value="scrum master">Scrum Master</option>
                                <option value="project manager">Project Manager</option>
                            </select>
                        </label>
                    </p>
                    <p>
                        <label className="boxTitle">
                            Allocation
                            <input
                                className="inputnumberBox"
                                type="number"
                                id="allocation"
                                min="1"
                                max="100"
                                name="allocation"
                                value={newResources.allocation}
                                onChange={handleChange}
                                required
                            />
                        </label>
                    </p>
                    <p>
                        <label className="boxTitle">
                            Cost per Hour
                            <input
                                className="inputnumberBox"
                                type="number"
                                step="0.01"
                                id="costhour"
                                name="costPerHour"
                                value={newResources.costPerHour}
                                onChange={handleChange}
                                required
                            />
                        </label>
                    </p>
                    <p>
                        <label className="boxTitle">
                            Start Date
                            <input
                                className="dateBox"
                                type="date"
                                id="hrstartdate"
                                name="startDate"
                                value={newResources.startDate}
                                onChange={handleChange}
                                required
                            />
                        </label>
                    </p>
                    <p>
                        <label className="boxTitle">
                            End Date
                            <input
                                className="dateBox"
                                type="date"
                                id="hrenddate"
                                name="endDate"
                                value={newResources.endDate}
                                onChange={handleChange}
                                min={newResources.startDate}
                                required
                            />
                        </label>
                    </p>
                    <p>
                        <input className="button-Submit" type="submit" value="Submit"/>
                    </p>
                </form>
            )}
            {showConfirmationModal && (
                <div className="modal">
                    <div onClick={toggleModal} className="overlay"></div>
                    <div className="modal-content">
                        <p className="confirmMessage">Are you sure you want to submit this form?</p>
                        <div className="form-data">
                            <table className="form-data-table">
                                <tbody>
                                <tr>
                                    <td><span className="form-data-label">Account:</span></td>
                                    <td>{newResources.email}</td>
                                </tr>
                                <tr>
                                    <td><span className="form-data-label">Role:</span></td>
                                    <td>{newResources.role}</td>
                                </tr>
                                <tr>
                                    <td><span className="form-data-label">Allocation:</span></td>
                                    <td>{newResources.allocation}</td>
                                </tr>
                                <tr>
                                    <td><span className="form-data-label">Cost per Hour:</span></td>
                                    <td>{newResources.costPerHour}</td>
                                </tr>
                                <tr>
                                    <td><span className="form-data-label">Start Date:</span></td>
                                    <td>{newResources.startDate}</td>
                                </tr>
                                <tr>
                                    <td><span className="form-data-label">End Date:</span></td>
                                    <td>{newResources.endDate}</td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                        <p></p>
                        <div className="modal-buttons-container">
                            <button className="cancel-modal" onClick={handleCancel}>
                                CANCEL
                            </button>
                            <button className="confirm-modal" onClick={handleConfirm}>
                                CONFIRM
                            </button>
                        </div>
                    </div>
                </div>
            )}
        </div>
    );
}

export default FormHumanResources;
