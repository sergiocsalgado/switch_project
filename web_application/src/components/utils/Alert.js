import React from "react";

export function showAlertModal(headingText, messageText) {
    const modal = document.createElement("div");
    modal.className = "alert-modal";

    const heading = document.createElement("h2");
    heading.textContent = headingText;

    const message = document.createElement("p");
    message.textContent = messageText;

    const closeButton = document.createElement("button");
    closeButton.textContent = "OK";
    closeButton.addEventListener("click", () => {
        document.body.removeChild(modal);
        window.location.reload();
    });

    modal.appendChild(heading);
    modal.appendChild(message);
    modal.appendChild(closeButton);

    document.body.appendChild(modal);
}
