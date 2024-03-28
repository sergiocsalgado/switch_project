import React, {useContext, useState} from "react";
import {createSprintAction} from "../../context/Actions";
import AppContext from "../../context/AppContext";
import "../formStyle.css";
import {useParams} from "react-router";
import {v4 as uuidv4} from "uuid";

function FormSprint() {
    const [showForm, setShowForm] = useState(false);
    const {dispatch, state} = useContext(AppContext);
    const {projects} = state;
    const {projectCode, status} = useParams();
    const actualStatus = status !== "Closed";

    const myUuid = uuidv4().slice(0, 8);
    console.log(myUuid);
    const [showConfirmationModal, setShowConfirmationModal] = useState(false);
    const [newSprint, setNewSprint] = useState({
        projectCode: projectCode,
        sprintID: myUuid,
        sprintNumber: "",
        startDate: "",
        endDate: "",
    });

    const handleSubmit = async (event) => {
        event.preventDefault();
        setShowConfirmationModal(true);
    };
    const handleConfirm = () => {
        // Form submission logic...
        createSprintAction(projectCode, dispatch, newSprint);
        setNewSprint({
            projectCode: projectCode,
            sprintID: myUuid,
            sprintNumber: "",
            startDate: "",
            endDate: "",
        });
        setShowForm(false); // Close the form
        setShowConfirmationModal(false);
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
        setNewSprint((prevNewSprint) => ({...prevNewSprint, [name]: value}));
    };

    return (
        <div>
            {actualStatus &&
                <button className="button-Add" onClick={handleShowForm}>
                    {showForm ? "Close" : "Add"}
                </button>}
            {showForm && (
                <form
                    className="form"
                    onSubmit={handleSubmit}
                >
                    <h1>Add Sprint</h1>
                    <p>
                        <label className="">
                            <input
                                className=""
                                type="text"
                                id="projectCode"
                                name="projectCode"
                                value={newSprint.projectCode}
                                required
                                readOnly
                                hidden="true"
                            />
                        </label>
                    </p>
                    <p>
                        <label className="boxTitle">
                            <input
                                className="inputnumberBox"
                                type="text"
                                id="sprintID"
                                name="sprintID"
                                value={newSprint.sprintID}
                                required
                                readOnly
                                hidden
                            />
                        </label>
                    </p>
                    <p>
                        <label className="boxTitle">
                            Number
                            <input
                                className="dateBox"
                                type="number"
                                id="sprintNumber"
                                min="1"
                                name="sprintNumber"
                                value={newSprint.sprintNumber || ""}
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
                                id="startDate"
                                name="startDate"
                                value={newSprint.startDate || ""}
                                onChange={handleChange}
                                title="Start date cannot be before today"
                                min={new Date().toISOString().slice(0, 10)}
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
                                id="endDate"
                                name="endDate"
                                value={newSprint.endDate || ""}
                                onChange={handleChange}
                                title="End date needs to be later than start date"
                                min={newSprint.startDate}
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
                                    <td><span className="form-data-label">Number:</span></td>
                                    <td>{newSprint.sprintNumber}</td>
                                </tr>
                                <tr>
                                    <td><span className="form-data-label">Start Date:</span></td>
                                    <td>{newSprint.startDate}</td>
                                </tr>
                                <tr>
                                    <td><span className="form-data-label">End Date:</span></td>
                                    <td>{newSprint.endDate}</td>
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

export default FormSprint;