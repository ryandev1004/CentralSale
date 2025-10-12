import { AuthProvider, useAuth } from './contexts/AuthorizationContext';
import Authorization from './components/auth/Authorization';
import Dashboard from './components/dashboard/Dashboard';
import { ItemProvider } from './contexts/ItemContext';

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
      <ItemProvider>
        <AppContent />
      </ItemProvider>
    </AuthProvider>
  );
}

export default App;