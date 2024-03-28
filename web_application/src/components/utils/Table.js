import React from 'react';
import TableHeader from './TableHeader';
import TableBody from './TableBody';

/**
 Renders a table with the data passed as props, or a message if no data is provided.

 @param props - An object containing the properties of the table.
 @param props.data - An array of objects representing the data to be displayed in the table.
 @param props.headers - An array of strings representing the names of the columns.
 @returns The rendered table with header and body, or a message if no data is provided.
 */

function Table(props){
    if(props.data.length > 0){
        return (
           <table border="1">
                <TableHeader headers = {props.headers} />
                <TableBody data={props.data} />
            </table>
         );
     } else {
        return (<h1>No data ....</h1>);
    }
}

export default Table;
