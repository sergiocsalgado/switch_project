import React from "react";

function TableBodyProfile({data}) {
    const rows = data.map((row, index) => {
        return (
            <tr key={index} style={{cursor: "pointer"}}>
                <td style={{ textAlign: 'center' }}>{row.profileID}</td>
                <td style={{ textAlign: 'center' }}>{row.description}</td>
            </tr>
        )
    });
    return (
        <tbody>{rows}</tbody>
    );
}

export default TableBodyProfile;