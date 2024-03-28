import React, {useContext, useState} from "react";
import AppContext from "../../context/AppContext";
import {createUSAction} from '../../context/Actions';
import "../formStyle.css"
import {useParams} from "react-router";
import {v4 as uuidv4} from 'uuid';
import {showAlertModal} from "../utils/Alert";


function FormUserStory() {
    const {dispatch,state} = useContext(AppContext);
    const myUuid = uuidv4().slice(0, 8);
    const {projects} = state;
    const { projectCode, status } = useParams();
    const actualStatus = status !== "Closed";
    const [newUS, setNewUS] = useState({
        projectCode: projectCode,
        userStoryID: myUuid,
        userStoryText: '',
        status: '',
        userStoryNumber: '',
        actor: '',
        priority: ''
    });
    const [showForm, setShowForm] = useState(false);
    const [showConfirmationModal, setShowConfirmationModal] = useState(false);
    const [textarea, setTextArea] = useState({});

    const handleFormSubmit = (event) => {
        event.preventDefault();
        console.log("Form Data:", newUS)
        setShowConfirmationModal(true);
    }

    const handleConfirm = () => {
        createUSAction(projectCode, dispatch, newUS)
        setNewUS({
            projectCode: projectCode,
            userStoryID: myUuid,
            userStoryText: '',
            status: '',
            userStoryNumber: '',
            actor: '',
            priority: ''
        })
        setShowForm(false); // Close the form
        setShowConfirmationModal(false)

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
    }

    const handleChange = (event) => {
        const {name, value} = event.target;
        setNewUS((prevNewUS) => ({...prevNewUS, [name]: value}));
    }

    return (
        <div>
            {actualStatus &&
            <button className="button-Add" onClick={handleShowForm}>
                {showForm ? "Close" : "Add"}
            </button>}
            {showForm &&(
                <form className="form" onSubmit={handleFormSubmit}>
                    <h1>Add UserStory</h1>
                    <p>
                        <label className="">
                            <input
                                className=""
                                type="text"
                                id="projectCode"
                                name="projectCode"
                                value={newUS.projectCode}
                                required
                                readOnly
                                hidden="true"
                            />
                        </label>
                    </p>
                    <p>
                        <label className="">
                            <input className=""
                                   type="text"
                                   name="userStoryID"
                                   value={newUS.userStoryID}
                                   required
                                   readOnly
                                   hidden
                            />
                        </label>
                    </p>

                    <label className="boxTitle">
                        Description
                        <textarea className="textBox"
                                  name="userStoryText"
                                  required
                                  value={textarea.userStoryText}
                                  onChange={handleChange}
                        />
                    </label>

                    <p>
                        <label className="boxTitle">
                            <input
                                className="statusBox"
                                type="text"
                                name="status"
                                value="Planned"
                                hidden="true"
                            />
                        </label>
                    </p>
                    <p>
                        <label className="boxTitle">
                            US Number
                            <input className="inputnumberBox"
                                   type="number"
                                   min="1"
                                   name="userStoryNumber"
                                   required
                                   value={newUS.userStoryNumber}
                                   onChange={handleChange}
                            />
                        </label>
                    </p>
                    <p>
                        <label className="boxTitle">
                            Actor
                            <input className="inputBox"
                                   type="text"
                                   name="actor"
                                   required
                                   value={newUS.actor}
                                   onChange={handleChange}

                            />
                        </label>
                    </p>

                    <label className="boxTitle">
                        Priority
                        <input className="inputnumberBox"
                               type="number"
                               min="1"
                               name="priority"
                               required
                               value={newUS.priority}
                               onChange={handleChange}
                        />
                    </label>
                    <p>
                        <input
                            className="button-Submit"
                            type="submit"
                            value="Submit"
                        />
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
                                    <td><span className="form-data-label">Description:</span></td>
                                    <td>{newUS.userStoryText}</td>
                                </tr>
                                <tr>
                                    <td><span className="form-data-label">Number:</span></td>
                                    <td>{newUS.userStoryNumber}</td>
                                </tr>
                                <tr>
                                    <td><span className="form-data-label">Actor:</span></td>
                                    <td>{newUS.actor}</td>
                                </tr>
                                <tr>
                                    <td><span className="form-data-label">Priority:</span></td>
                                    <td>{newUS.priority}</td>
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
    )
}
export default FormUserStory;


