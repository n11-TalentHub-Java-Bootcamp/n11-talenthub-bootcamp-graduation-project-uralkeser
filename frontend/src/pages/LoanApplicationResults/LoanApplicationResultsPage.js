import React from "react";
import ApplicationForm from "../../component/ApplicationForm/ApplicationForm";
import AppConfig from "../../config/AppConfig";
import InfoArea from "../../component/InfoArea/InfoArea";


class LoanApplicationResultsPage extends React.Component {

  constructor(props) {
    super(props);
    this.state = {
      isSubmit: false,
      response: 'success',
      data: []
    };
  }

  handleSubmit = (e) => {
    e.preventDefault();
    console.log(this.state);
    this.submitForm();
  }

  submitForm = () => {

    const uri = "http://localhost:8080/api/v1/clients/loan-applications/"+this.state.ssn+"/"+this.state.birthdate;;

    const requestOptions = {
      method: "GET"
    }

    fetch(uri, requestOptions)
      .then(response => {
        if (response.status != "200") {
          this.setState({
            response: 'unsuccessful',
          });
          return;
        }
        return response.json();
      }).then(res => {
        if (res) {
          this.setState({
            response: 'success',
            data: res
          });
        }
      })
      .catch(error => {
        console.log(error);
        this.setState({
          response: 'unsuccessful',
        });
      });
    this.setState({
      isSubmit: true
    });
  }

  handleInputChange = (event) => {
    const target = event.target;
    const value = target.value;
    const name = target.id;

    this.setState({
      [name]: value
    });

  }

  render(){
    return (
     <>
        { this.state.isSubmit ?  <InfoArea response={this.state.response} data={this.state.data} ></InfoArea> : <ApplicationForm title="Başvuru Sonuçları" button="Başvuru Sonuçlarımı Göster" formItems={AppConfig.showResultActionFields} onSubmit={this.handleSubmit} onChange={this.handleInputChange} />}
      </>
    );
  }
}

export default LoanApplicationResultsPage;