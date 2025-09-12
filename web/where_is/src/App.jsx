import { BrowserRouter as Router, Routes, Route } from 'react-router-dom'
import './App.css'
import BasicLayout from './components/layout/BasicLayout'
import LoginEmail from './pages/auth/LoginEmail'
import LoginMain from './pages/auth/LoginMain'
import NotFound from './pages/NotFound'
import Main from './pages/main'
import Detail from './pages/Detail'
import DetailSummary from './pages/DetailSummary' 
import RegisterEmail from './pages/auth/RegisterEmail'
import ForgetAccount from './pages/auth/ForgetAccount'
import PlaceReport from './pages/PlaceReport'
import PlaceRequest from './pages/PlaceRequest'

function App() {
  return (

      <Router>
        <Routes>
          {/* 일반 페이지 레이아웃 */}
          <Route element={<BasicLayout />}>
            <Route path="/auth/login/main" element={<LoginMain />} />
            <Route path="/auth/login/email" element={<LoginEmail />} />
            <Route path="/auth/login/email/register" element={<RegisterEmail />} />
            <Route path="/auth/login/email/forget/:type" element={<ForgetAccount />} />
            <Route path="/" element={<Main />} />
            <Route path="/smoking-area/:id" element={<Detail />} />
            <Route path="/smoking-area/:id/summary" element={<DetailSummary />} />
            <Route path="/report" element={<PlaceReport />} />
            <Route path="/request" element={<PlaceRequest />} />
          </Route>
          {/* 404 페이지 - 정의되지 않은 모든 경로 */}
          <Route path="*" element={<NotFound />} />
        </Routes>
      </Router>

  )
}

export default App