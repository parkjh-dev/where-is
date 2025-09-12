import React from 'react';

function MyLocationButton() {
  return (
    <a href="#" className="btn_mylct" style={{
      display: 'inline-block',
      background: 'white',
      border: '1px solid #d1d5db',
      borderRadius: '4px',
      width: '40px',
      height: '40px',
      textDecoration: 'none',
      color: '#374151',
      boxShadow: '0 1px 3px rgba(0,0,0,0.1)',
      cursor: 'pointer',
      transition: 'all 0.2s ease',
      marginRight: '20px'
    }} onMouseOver={(e) => e.target.style.backgroundColor = '#f9fafb'} onMouseOut={(e) => e.target.style.backgroundColor = 'white'}>
      <span style={{
        display: 'flex',
        alignItems: 'center',
        justifyContent: 'center',
        width: '100%',
        height: '100%'
      }}>
        <img src="/src/assets/my-location.png" alt="my-location" style={{width: '20px', height: '20px', objectFit: 'contain'}} />
      </span>
    </a>
  );
}

export default MyLocationButton;