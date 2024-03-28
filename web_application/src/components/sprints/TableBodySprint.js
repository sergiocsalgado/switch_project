import React, {useState} from "react";
import {Link, useParams} from "react-router-dom";
import {fetchUpdateSprintStatusAction, fetchUpdateProductBacklogAction} from "../../context/Actions"
import { useContext } from "react";
import AppContext from "../../context/AppContext";
import {showAlertModal} from "../../components/utils/Alert";


function TableBodySprint({data}) {
    // get the project code from URL with params
    const {projectCode} = useParams();
    const {dispatch, state} = useContext(AppContext);

    const handleStartSprintStatus = async (sprintID) => {
          // create a SprintDTO object with the sprintID
          const sprintDTO = { sprintID };
          // verify if the fetchUpdateSprintStatusAction get anything else that not a 200/ok as response to the API call
          const response = await fetchUpdateSprintStatusAction(projectCode, sprintDTO, dispatch);
          console.log("tableresposce",response);
          if (response.status !== 200) {
            const errorMessage = `Failed to start sprint: ${response.statusText}`;
            showAlertModal(errorMessage);
          }
        };

    const handleCloseSprintAndUpdateProductBacklog = async (sprintID) => {
            // update the product backlog
            fetchUpdateProductBacklogAction(projectCode, dispatch);
            // update the sprint status
            const sprintDTO = {sprintID};
            fetchUpdateSprintStatusAction(
                projectCode,
                sprintDTO,
                dispatch,
            )
    };

    const rows = data.map((row, index) => {
        const isPlanned = row.sprintStatus === "planned";
        const isOpen = row.sprintStatus === "open";
        const isClosed = row.sprintStatus === "closed";
        return (
            <tr key={index} style={{cursor: "pointer"}}>
                <td style={{textAlign: 'center'}}>{row.sprintNumber}</td>
                <td style={{textAlign: 'center'}}>{row.startDate}</td>
                <td style={{textAlign: 'center'}}>{row.endDate}</td>
                <td style={{textAlign: 'center'}}>{row.sprintStatus}</td>
                <td>
                    <div className="button-wrapper">
                        <Link
                            to={`/projects/${projectCode}/sprints/${row.sprintID}/${row.sprintStatus}/sprint-backlog`}>
                            <button className="button-resources">Sprint Backlog</button>
                        </Link>
                    </div>
                </td>
                {isPlanned && (
                    <>
                        <td>
                            <div>
                                <button
                                    className="button-resources"
                                    onClick={() => handleStartSprintStatus(row.sprintID)
                                        .then(() => showAlertModal("Sprint started successfully"))
                                        .catch(() => showAlertModal("Failed to start sprint"))
                                    }
                                >
                                    Start Sprint
                                </button>
                            </div>
                        </td>
                    </>
                )}
                {isOpen && (
                    <>
                        <td>
                            <div>
                                <button
                                    className="button-resources"
                                    onClick={() => handleCloseSprintAndUpdateProductBacklog(row.sprintID)
                                        .then(() => showAlertModal("Sprint closed and productBacklog changed successfully"))
                                        .catch(() => showAlertModal("Failed to close sprint and change productBacklog"))
                                    }
                                >
                                    Close Sprint
                                </button>
                            </div>
                        </td>
                    </>
                )}
            </tr>
        );
    });


    return (
        <tbody>
            {rows}
        </tbody>
    );
}

export default TableBodySprint;
