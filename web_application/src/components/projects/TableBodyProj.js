import React, {useState} from 'react';
import {Link} from "react-router-dom";
import "../../App.css"

/**
 Renders the table body with the data passed as props.

 @param body - An array of objects representing the rows of the table.
 @returns The rendered table body.
 */

function TableBodyProj({data}) {

    const [expandedRowIndex, setExpandedRowIndex] = useState(null);

    const handleToggleRow = (index) => {
        setExpandedRowIndex(index === expandedRowIndex ? null : index);
    };
    const rows = data.map((row, index) => {
        return (

            <tr key={index} style={{cursor: "pointer"}} className='table-body'>
                <td style={{textAlign: 'center'}}>{row.name}</td>
                <td style={{textAlign: 'center'}}>{row.description}</td>
                <td style={{textAlign: 'center'}}>{row.customer}</td>
                <td style={{textAlign: 'center'}}>{row.businessSector}</td>
                <td style={{textAlign: 'center'}}>{row.typology}</td>
                <td style={{textAlign: 'center'}}>{row.startDate}</td>
                <td style={{textAlign: 'center'}}>{row.endDate}</td>
                <td style={{textAlign: 'center'}}>{row.status}</td>
                <td style={{textAlign: 'center'}}>{row.sprintDuration}</td>
                <td style={{textAlign: 'center'}}>{row.numberOfPlannedSprints}</td>
                <td style={{textAlign: 'center'}}>{row.budget}</td>

                <td>
                    <div className={`button-container ${index === expandedRowIndex ? 'expanded' : ''}`}>
                        {index === expandedRowIndex ? (
                            <div className="button-wrapper">
                                <Link to={`/projects/${row.projectCode}/${row.status}/product-backlog`}>
                                    <button className='button-pb'>Product Backlog</button>
                                </Link>
                                <Link to={`/projects/${row.projectCode}/${row.status}/sprints`}>
                                    <button className='button-sprint custom-height'>Sprints</button>
                                </Link>
                                <Link to={`/projects/${row.projectCode}/${row.status}/project-team`}>
                                    <button className='button-resources'>Project Team</button>
                                </Link>
                                <Link to={`/projects/${row.projectCode}/scrum-board`}>
                                    <button className='button-sb'>Scrum Board</button>
                                </Link>
                            </div>
                        ) : (
                            <button className='button-more' onClick={() => handleToggleRow(index)}>
                                ...
                            </button>
                        )}
                    </div>
                </td>
            </tr>
        )
    })
    return (
        <tbody>{rows}</tbody>
    );
}


export default TableBodyProj


