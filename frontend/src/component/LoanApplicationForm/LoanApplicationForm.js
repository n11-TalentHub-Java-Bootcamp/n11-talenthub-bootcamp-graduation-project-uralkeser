import AppConfig from "../../config/AppConfig";
import FormItem from "../FormItem/FormItem";

const ApplicationForm = (props) =>{

  return(
    <form id="customer-form" onSubmit={props.onSubmit}>
      {
        props.formItems.map((element)=>{
          return (
            <FormItem key={element.tr} field={element.tr} id={element.eng} handleInputChange={props.onChange} />
          )
        })
      }
      <input type="submit" value={AppConfig.applyButton} />
    </form>
      
  );
}

export default ApplicationForm;