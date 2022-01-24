import logoSource from '../../assets/logo.png';

const Header = () =>{
  return(
    <button>
      <header className='header'>
        <img className='header-logo' src={logoSource} alt=''/>
      </header>
    </button>
  )
}

export default Header;