import React, {useState} from "react";
import "./ConfirmationModal.css";

export default function ConfirmationModal({formData, onConfirm}) {
    const [modal, setModal] = useState(false);

    const toggleModal = () => {
        setModal(!modal);
    };

    const handleConfirm = () => {
        onConfirm();
        toggleModal();
    };
    //
    // if (modal) {
    //     document.body.classList.add('active-modal')
    // } else {
    //     document.body.classList.remove('active-modal')
    // }

    return (
        <>
            <button onClick={toggleModal} className="button-Submit">
                Submit
            </button>

            {modal && (
                <div className="modal">
                    <div onClick={toggleModal} className="overlay"></div>
                    <div className="modal-content">
                        <p>Are you sure you want to submit this form?</p>
                        <pre>{JSON.stringify(formData,null,2)}</pre>
                        <div className="modal-buttons">
                            <button className="cancel-modal" onClick={toggleModal}>
                                CANCEL
                            </button>
                            <button className="confirm-modal" onClick={handleConfirm}>
                                CONFIRM
                            </button>
                        </div>
                    </div>
                </div>
            )}
        </>
    );
}