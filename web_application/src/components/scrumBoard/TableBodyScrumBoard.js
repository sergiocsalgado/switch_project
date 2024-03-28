import React, { useEffect, useState } from 'react';
import { changeStatusAction } from "../../context/Actions";
import { useParams } from "react-router-dom";
import FormScrumBoard from "./FormScrumBoard";

function TableBodySB({ data }) {
    const [rowData, setRowData] = useState(data);
    const { projectCode, userStoryID } = useParams();

    useEffect(() => {
        setRowData(data);
    }, [data]);

    const handleStatusChange = (index, newStatus) => {
        const updatedData = [...rowData];
        updatedData[index].status = newStatus;
        setRowData(updatedData);

        const userStory = updatedData[index];
        changeStatusAction(userStory.projectCode, userStory.userStoryID, userStory);
    };

    const rows = rowData.map((row, index) => {
        return (
            <tr key={index} style={{ cursor: "pointer" }}>
                <td style={{ textAlign: 'center' }}>{row.userStoryNumber}</td>
                <td style={{ textAlign: 'center' }}>{row.userStoryText}</td>
                <td style={{ textAlign: 'center' }}>{row.status}</td>
                <td style={{ textAlign: 'center' }}>{row.actor}</td>
                <td style={{ textAlign: 'center' }}>{row.priority}</td>
                <td>
                    <FormScrumBoard
                        row={row}
                        index={index}
                        projectCode={projectCode}
                        userStoryID={userStoryID}
                        handleStatusChange={handleStatusChange}
                    />
                </td>
            </tr>
        );
    });

    return <tbody>{rows}</tbody>;
}

export default TableBodySB;
