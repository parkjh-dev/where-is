import React from 'react'

const AIAssistant = () => {
  return (
    <img 
      src="/logo.png" 
      alt="AI Assistant" 
      title="AI Assistant"
      className="fixed bottom-6 right-6 w-12 h-12 rounded-full shadow-xl hover:scale-105 active:scale-95 transition-transform cursor-pointer object-cover"
      onClick={() => alert('AI Assistant 준비 중입니다.')}
    />
  )
}

export default AIAssistant