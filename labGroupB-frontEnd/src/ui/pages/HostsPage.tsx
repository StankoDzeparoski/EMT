import { Box, Typography } from '@mui/material';
import useHosts from '../../hooks/useHosts';
import HostsList from '../components/host/HostsList';

const HostsPage = () => {
  const { hosts, loading, error } = useHosts();

  return (
    <Box>
      <Typography variant="h4" sx={{ marginBottom: 4, fontWeight: 'bold' }}>
        Hosts
      </Typography>
      <HostsList hosts={hosts} loading={loading} error={error} />
    </Box>
  );
};

export default HostsPage;
