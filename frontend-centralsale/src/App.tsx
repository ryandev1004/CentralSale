import { AuthProvider, useAuth } from './contexts/AuthorizationContext';
import Authorization from './components/auth/Authorization';
import Dashboard from './components/dashboard/Dashboard';

function AppContent() {
  const { isAuthenticated, loading } = useAuth();
  
  if (loading) {
    return (
      <div className="flex items-center justify-center min-h-screen bg-gray-50">
        <div className="text-xl font-semibold">Loading...</div>
      </div>
    );
  }
  
  return (
    <>
      {!isAuthenticated && <Authorization />}
      <Dashboard />
    </>
  );
}

function App() {
  return (
    <AuthProvider>
      <AppContent />
    </AuthProvider>
  );
}

export default App;