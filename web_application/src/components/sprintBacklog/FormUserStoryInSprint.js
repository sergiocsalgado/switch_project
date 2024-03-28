import React, { useContext, useEffect, useState } from "react";
import AppContext from "../../context/AppContext";
import {fetchUserStoryInSprint, patchPbToSbAction} from "../../context/Actions";
import "../formStyle.css";
import { useParams } from "react-router";
import { showAlertModal } from "../utils/Alert";

function FormUserStoryInSprint() {
  const { dispatch } = useContext(AppContext);
  const [showForm, setShowForm] = useState(false);
  const [userStories, setUserStories] = useState([]);
  const [newUS, setNewUS] = useState({});
  const { projectCode, sprintID, sprintStatus } = useParams();
  const [showConfirmationModal, setShowConfirmationModal] = useState(false);
 
  const actualSprintStatus = sprintStatus === "planned";

  const handleSubmit = async (event) => {
    event.preventDefault();
    setShowConfirmationModal(true);
  };

  const handleConfirm = () => {
    patchPbToSbAction(
      projectCode,
      newUS.userStoryID,
      sprintID,
      newUS,
      dispatch
    );
    setNewUS({});
    setShowConfirmationModal(false);
    showAlertModal("Success!", "Form submitted successfully!");
    fetchUserStoryInSprint(projectCode, sprintID, dispatch);
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
    const selectedUserStory = userStories.find(
      (value) => value.userStoryNumber === event.target.value
    );
    if (selectedUserStory) {
      setNewUS({
        userStoryNumber: event.target.value,
        userStoryID: selectedUserStory.userStoryID,
      });
    } else {
      setNewUS({
        userStoryNumber: event.target.value,
        userStoryID: null,
      });
    }
  };

  const fetchProductBacklog = async () => {
    try {
      const response = await fetch(
        `http://localhost:8080/projects/${projectCode}/product-backlog`
      );
      const data = await response.json();
      console.log("productbacklog:", data);
      setUserStories(data);

      // Update dropdown options
      const userStoryOptions = document.getElementById("userStoryOptions");
      userStoryOptions.innerHTML = ""; // Clear existing options

      data.forEach((userStory) => {
        const option = document.createElement("option");
        option.value = userStory.userStoryID;
        option.textContent = userStory.userStoryText;
        userStoryOptions.appendChild(option);
      });
    } catch (error) {
      console.error("Error fetching productBacklog:", error);
    }
  };

  useEffect(() => {
    fetchProductBacklog();
  }, []);

  return (
    <div>
      {actualSprintStatus && (
        <button className="button-Add" onClick={handleShowForm}>
          {showForm ? "Close" : "Add"}
        </button>
      )}
      {showForm && (
        <form className="formStory" onSubmit={handleSubmit}>
          <label className="boxTitle">
            UserStory
            <select
              className="dropdownlBox"
              name="userStory"
              value={newUS.userStoryNumber || ""}
              onChange={handleChangeInput}
              required
              title="ℹ Please select an user story"
            >
              <option selected disabled value="">
                Select
              </option>
              {userStories.map((value) => (
                <option key={value.userStoryID} value={value.userStoryNumber}>
                  {value.userStoryNumber} | {value.userStoryText}
                </option>
              ))}
            </select>
          </label>
          <datalist id="userStoryOptions"></datalist>
          <p>
            <input className="button-Submit" type="submit" value="Submit" />
          </p>
        </form>
      )}
      {showConfirmationModal && (
        <div className="modal">
          <div onClick={toggleModal} className="overlay"></div>
          <div className="modal-content">
            <p className="confirmMessage">
              Are you sure you want to submit this form?
            </p>
            <div className="form-data">
              <table className="form-data-table">
                <tbody>
                  <tr>
                    <td>
                      <span className="form-data-label">US Number:</span>
                    </td>
                    <td>{newUS.userStoryNumber}</td>
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

export default FormUserStoryInSprint;
