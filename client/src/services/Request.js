const Request = {

    server : "http://localhost:8080/",
    getStudents() {
        return fetch(this.server + "student/").then(res => res.json());
    },
    getStudentsByStudyGroup(group_id) {
        return fetch(this.server + "study_group/" + group_id + "/students").then(res => res.json());
    },
    getJournalBySTudyGroup(group_id) {
        return fetch(this.server + "study_group/" + group_id + "/journal").then(res => res.json());
    },
    getAllGroups() {
        return fetch(this.server + "study_group/").then(res => res.json());
    }

};

export default Request
