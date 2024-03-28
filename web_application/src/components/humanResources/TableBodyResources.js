import React from 'react';

function TableBodyResources({ data }) {
    const rows = data.map((row,index) =>{
        return(
            <tr key={index} style={{cursor: "pointer"}}>
                <td style={{ textAlign: 'center' }}>{row.email}</td>
                <td style={{ textAlign: 'center' }}>{row.role}</td>
                <td style={{ textAlign: 'center' }}>{row.allocation}</td>
                <td style={{ textAlign: 'center' }}>{row.costPerHour}</td>
                <td style={{ textAlign: 'center' }}>{row.currency}</td>
                <td style={{ textAlign: 'center' }}>{row.startDate}</td>
                <td style={{ textAlign: 'center' }}>{row.endDate}</td>
            </tr>
        )
    });
    return(
        <tbody>{rows}</tbody>
    )
}
export default TableBodyResources;
