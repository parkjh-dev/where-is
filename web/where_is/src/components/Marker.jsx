import React from 'react';

function Marker({ type }) {
  const basePath = '/src/assets';

  const getImagePath = (markerType) => {
    switch (markerType) {
      case 'smoking':
        return `${basePath}/smoking_marker.png`;
      case 'toilet':
        return `${basePath}/toilet_marker.png`;
      case 'trash':
        return `${basePath}/trash-can_marker.png`;
      default:
        return `${basePath}/toilet_marker.png`; // 기본값
    }
  };

  const imagePath = getImagePath(type);

  return (
    <div style={{width: '40px', height: '40px', display: 'flex', alignItems: 'center', justifyContent: 'center'}}>
      <img src={imagePath} style={{width: '40px', height: '40px', objectFit: 'contain'}} alt={type} />
    </div>
  );
}

export default Marker;