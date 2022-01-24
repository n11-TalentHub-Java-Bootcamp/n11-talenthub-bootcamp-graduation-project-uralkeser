import FormItem from "../FormItem/FormItem";

const ApplicationForm = (props) =>{
  return(
    <form className="application-form" onSubmit={props.onSubmit}>
      {
        props.formItems.map((element)=>{
          return (
            <FormItem key={element} field={element} />
          )
        })
      }
      <input type="submit" value="Başvuru Yap" />
    </form>
  );
}

export default ApplicationForm;