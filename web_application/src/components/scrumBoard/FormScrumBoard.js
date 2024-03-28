import React, { useContext, useState } from 'react';
import { changeStatusService } from "../../services/ScrumBoardService";
import { changeStatusAction } from "../../context/Actions";
import AppContext from "../../context/AppContext";

function FormScrumBoard({ row, projectCode }) {
    const { dispatch } = useContext(AppContext);
    const [selectedStatus, setSelectedStatus] = useState('');
    const [showConfirmationModal, setShowConfirmationModal] = useState(false);
    const [isSubmitting, setIsSubmitting] = useState(false);

    const handleStatusChange = (event) => {
        const newStatus = event.target.value;
        setSelectedStatus(newStatus);
        setShowConfirmationModal(true);
    };

    const handleCancel = () => {
        setSelectedStatus('');
        setShowConfirmationModal(false);
    };

    const toggleModal = () => {
        setShowConfirmationModal(!showConfirmationModal);
    };

    const handleConfirm = () => {
        setIsSubmitting(true);
        const userStory = {
            ...row,
            status: selectedStatus,
            projectCode: projectCode,
            userStoryID: row.userStoryID
        };

        changeStatusService(
            userStory.projectCode,
            userStory.userStoryID,
            userStory,
            (data, updatedUserStory) => {
                dispatch(changeStatusAction(updatedUserStory));
                setIsSubmitting(false);
                setShowConfirmationModal(false);
                window.location.reload();
            }
        );

    };

    return (
        <div>
            <select value={selectedStatus} onChange={handleStatusChange} className="minidropdownlBox">
                <option value="">-- Select --</option>
                <option value="planned">planned</option>
                <option value="running">running</option>
                <option value="finished">finished</option>
            </select>

            {showConfirmationModal && (
                <div className="modal">
                    <div onClick={toggleModal} className="overlay"></div>
                    <div className="modal-content">
                        <p className="confirmMessage">Are you sure you want to submit this form?</p>
                        <div className="form-data">
                            <table className="form-data-table">
                                <tbody>
                                <tr>
                                    <td><span className="form-data-label">Status:</span></td>
                                    <td>{selectedStatus}</td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                        <p></p>
                        <div className="modal-buttons-container">
                            <button className="cancel-modal" onClick={handleCancel}>
                                CANCEL
                            </button>
                            <button className="confirm-modal" onClick={handleConfirm} disabled={isSubmitting}>
                                {isSubmitting ? 'SUBMITTING...' : 'CONFIRM'}
                            </button>
                        </div>
                    </div>
                </div>
            )}
        </div>
    );
}

export default FormScrumBoard;
