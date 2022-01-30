import AppConfig from "../../config/AppConfig";
import FormItem from "../FormItem/FormItem";

const ApplicationForm = (props) =>{

  return(
    <div className="">
      <h2>{props.title}</h2>
      <form id="customer-form" onSubmit={props.onSubmit}>
        {
          props.formItems.map((element)=>{
            return (
              <FormItem key={element.tr} field={element.tr} id={element.eng} handleInputChange={props.onChange} />
            )
          })
        }
        <input type="submit" value={props.button} />
      </form>
    </div>
  );
}

export default ApplicationForm;