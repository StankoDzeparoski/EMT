import { Box, Typography } from '@mui/material';
import useAuthLogs from '../../hooks/useAuthLogs';
import LogsList from '../components/logs/LogsList';

const LogsPage = () => {
  const { authLogs, loading, error } = useAuthLogs();

  return (
    <Box>
      <Typography variant="h4" sx={{ marginBottom: 4, fontWeight: 'bold' }}>
        Authentication Logs
      </Typography>
      <LogsList authLogs={authLogs} loading={loading} error={error} />
    </Box>
  );
};

export default LogsPage;
