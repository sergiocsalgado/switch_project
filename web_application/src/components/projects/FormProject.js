import React, {useContext, useEffect, useState} from "react";
import AppContext from "../../context/AppContext";
import "../formStyle.css";
import {createProjectAction} from '../../context/Actions';
import {v4 as uuidv4} from 'uuid';
import ConfirmationModal from "../ConfirmationModal/ConfirmationModal";
import {showAlertModal} from "../utils/Alert";
import Select from "react-select";
import projects from "../../pages/Projects";

function FormProject() {
    const {dispatch} = useContext(AppContext);
    const myUuid = uuidv4().slice(0, 8);
    const [newProject, setNewProject] = useState({
        projectCode: myUuid,
        name: "",
        description: "",
        customer: "",
        businessSector: "",
        typology: "",
        startDate: "",
        endDate: "",
        status: "",
        sprintDuration: "",
        numberOfPlannedSprints: "",
        budget: ""
    });
    const [showForm, setShowForm] = useState(false);
    const [customers, setCustomers] = useState([]);
    const [businessSectors, setBusinessSectors] = useState([]);
    const [typologies, setTypologies] = useState([]);
    const [showConfirmationModal, setShowConfirmationModal] = useState(false);

    const handleSubmit = (event) => {
        event.preventDefault();
        const newProjectWithCode = {
            ...newProject,
            budget: `${newProject.budget}EUR`
        };
        console.log("data:", newProject)

        setShowConfirmationModal(true);
        setNewProject(newProjectWithCode);
    };

    const handleConfirm = () => {
        createProjectAction(dispatch, newProject)

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
    };

    const handleChangeInput = (event) => {
        const {name, value} = event.target;
        const inputValue = String(value);
        setNewProject((prevInputs) => ({...prevInputs, [name]: inputValue}));
    };

    useEffect(() => {
        const fetchCustomers = async () => {
            try {
                const response = await fetch('http://localhost:8080/customers');
                const data = await response.json();
                console.log("Customers data:", data);
                setCustomers(data);
            } catch (error) {
                console.error('Error fetching customers:', error);
            }
        };

        fetchCustomers();
    }, []);

    useEffect(() => {
        const fetchBusinessSectories = async () => {
            try {
                const response = await fetch('http://localhost:8080/business-sectors');
                const data = await response.json();
                console.log("Business data:", data);
                setBusinessSectors(data);
            } catch (error) {
                console.error('Error fetching bs:', error);
            }
        };

        fetchBusinessSectories();
    }, []);

    useEffect(() => {
        const fetchTypologies = async () => {
            try {
                const response = await fetch('http://localhost:8080/typologies');
                const data = await response.json();
                console.log("typology:", data);
                setTypologies(data);
            } catch (error) {
                console.error('Error fetching typology:', error);
            }
        };

        fetchTypologies();
    }, []);

    // Customer drop-down
    const handleCustomerChange = (selectedOption) => {
        setNewProject((prevNewProject) => ({
            ...prevNewProject,
            customer: selectedOption.value,
        }));
    };
    const customerOptions = customers.map((customer) => ({
        value: customer.name,
        label: `${customer.name}`,
    }));

    // Business sector drop-down
    const handleBusinessChange = (selectedOption) => {
        setNewProject((prevNewProject) => ({
            ...prevNewProject,
            businessSector: selectedOption.value,
        }));
    };
    const businessOptions = businessSectors.map((business) => ({
        value: business.description,
        label: `${business.description}`,
    }));

    // Typology sector drop-down
    const handleTypologyChange = (selectedOption) => {
        setNewProject((prevNewProject) => ({
            ...prevNewProject,
            typology: selectedOption.value,
        }));
    };
    const typologyOptions = typologies.map((typology) => ({
        value: typology.typologyDescription,
        label: `${typology.typologyDescription}`,
    }));

    return (
        <div>
            <button className="button-Add-Project" onClick={handleShowForm}>
                {showForm ? "Close" : "Add"}
            </button>
            {showForm && (
                <form className="form" onSubmit={handleSubmit}>
                    <h1>Add Project</h1>
                    <p>
                        <label className="boxTitle">
                            <input
                                className=""
                                type="text"
                                name="projectCode"
                                required
                                value={newProject.projectCode}
                                readOnly
                                hidden
                            />
                        </label>
                    </p>
                    <p>
                        <label className="boxTitle">
                            Name
                            <input
                                className="inputBox"
                                placeholder="Write the name"
                                type="text"
                                name="name"
                                title="ℹ Name should only contain letters and spaces"
                                required
                                value={newProject.name || ""}
                                onChange={handleChangeInput}
                            />
                        </label>
                    </p>
                    <p>
                        <label className="boxTitle">
                            Description
                            <input
                                className="textBox"
                                placeholder="Write the description"
                                type="text"
                                name="description"
                                pattern=".{1,200}"
                                title="ℹ Please enter a description between 3 and 200 characters"
                                value={newProject.description || ""}
                                onChange={handleChangeInput}
                                maxLength={200}
                                required
                            />

                        </label>
                    </p>
                    <p>
                        <label className="boxTitle">
                            Customer
                        </label>
                        <Select
                            options={customerOptions}
                            onChange={handleCustomerChange}
                            className="select-dropdown"
                        />
                    </p>
                    <p>
                        <label className="boxTitle">
                            Business Sector
                        </label>
                        <Select
                            options={businessOptions}
                            onChange={handleBusinessChange}
                            className="select-dropdown"
                        />
                    </p>
                    <p>
                        <label className="boxTitle">
                            Typology
                        </label>
                        <Select
                            options={typologyOptions}
                            onChange={handleTypologyChange}
                            className="select-dropdown"
                        />
                    </p>
                    <p>
                        <label className="boxTitle">
                            Start Date
                            <input
                                className="dateBox"
                                type="date"
                                name="startDate"
                                pattern="\d{2}-\d{2}-\d{4}"
                                title="ℹ Please enter a valid date in the format DD-MM-YYYY"
                                min={new Date().toISOString().slice(0, 10)}
                                value={newProject.startDate || ""}
                                onChange={handleChangeInput}
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
                                name="endDate"
                                pattern="\d{2}-\d{2}-\d{4}"
                                title="ℹ Please enter a valid date in the format DD-MM-YYYY"
                                min={new Date().toISOString().slice(0, 10)}
                                value={newProject.endDate || ""}
                                onChange={handleChangeInput}
                            />
                        </label>
                    </p>
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
                            Sprint Duration
                            <select
                                className="dropdownlBox"
                                name="sprintDuration"
                                value={newProject.sprintDuration || ""}
                                onChange={handleChangeInput}
                                required
                                title="ℹ Please select a Sprint Duration"
                            >
                                <option value=""></option>
                                <option value="2">2</option>
                                <option value="3">3</option>
                                <option value="4">4</option>
                            </select>
                        </label>
                    </p>
                    <p>
                        <label className="boxTitle">
                            Number of Planned Sprints
                            <input
                                className="inputnumberBox"
                                type="number"
                                min="1"
                                name="numberOfPlannedSprints"
                                required
                                value={newProject.numberOfPlannedSprints || ""}
                                onChange={handleChangeInput}
                            />
                        </label>
                    </p>
                    <p>
                        <label className="boxTitle">
                            Budget
                            <input
                                className="inputBox"
                                placeholder="e.g:1000"
                                type="text"
                                name="budget"
                                value={newProject.budget || ""}
                                onChange={handleChangeInput}
                                pattern="^\d+(\.\d{1,2})?$"
                                title="ℹ Please enter a valid budget (e.g. 1000)"
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
                                    <td><span className="form-data-label">Name:</span></td>
                                    <td>{newProject.name}</td>
                                </tr>
                                <tr>
                                    <td><span className="form-data-label">Description:</span></td>
                                    <td>{newProject.description}</td>
                                </tr>
                                <tr>
                                    <td><span className="form-data-label">Customer:</span></td>
                                    <td>{newProject.customer}</td>
                                </tr>
                                <tr>
                                    <td><span className="form-data-label">Business Sector:</span></td>
                                    <td>{newProject.businessSector}</td>
                                </tr>
                                <tr>
                                    <td><span className="form-data-label">Typology:</span></td>
                                    <td>{newProject.typology}</td>
                                </tr>
                                <tr>
                                    <td><span className="form-data-label">Start Date:</span></td>
                                    <td>{newProject.startDate}</td>
                                </tr>
                                <tr>
                                    <td><span className="form-data-label">End Date:</span></td>
                                    <td>{newProject.endDate}</td>
                                </tr>
                                <tr>
                                    <td><span className="form-data-label">Sprint Duration:</span></td>
                                    <td>{newProject.sprintDuration}</td>
                                </tr>
                                <tr>
                                    <td><span className="form-data-label">Number of planned sprints:</span></td>
                                    <td>{newProject.numberOfPlannedSprints}</td>
                                </tr>
                                <tr>
                                    <td><span className="form-data-label">Budget:</span></td>
                                    <td>{newProject.budget}</td>
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

export default FormProject;
