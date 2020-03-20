import React from 'react';
import { Table } from "react-bootstrap";

class JournalTable extends React.Component {
    render() {
        return <Table bordered>
            <thead>
            <tr>
                <th>#</th>
                <th>ФИО</th>
                {this.props.subjects.map((subj, index) => {
                    return <th key={subj.index}>{subj.short_name} - {subj.type}</th>
                 })}
            </tr>
            </thead>
            <tbody>
            {this.props.marks.map((student, index) => {
                return <tr key={student.id}>
                    <td>{student.id}</td>
                    <td>{student.surname} {student.name} {student.last_name}</td>
                        {this.props.subjects.map((subj, i) => {
                            return student.marks[subj.index] ? <td key={student.id + "-" + subj.index} style = {student.marks[subj.index].mark_id == 4 || student.marks[subj.index].mark_id == 6? {background:'#FFAB91'} : {}}>
                                {student.marks[subj.index].value} { student.marks[subj.index].mark_id == 4 || student.marks[subj.index].mark_id == 6 ? <span key={"re"+student.id + "-" + subj.index}>| Пересдач: ({student.marks[subj.index].count})</span> : null} </td>:  <td keys={"empty"+ subj.index + "_" + student.id}></td>
                        })}
                </tr>
            })}
            </tbody>
        </Table>
    }
}
export default JournalTable;