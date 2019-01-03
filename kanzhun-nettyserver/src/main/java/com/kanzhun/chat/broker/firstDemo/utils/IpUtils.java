package com.kanzhun.chat.broker.firstDemo.utils;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.StringTokenizer;

/**
 * @author:xing.yl
 * @date: 18/11/11
 */
public class IpUtils {

        private static int innerIp = 0;
        private static int outerIp = 0;

        // 排除IP：高防、SLB
        private static final String IGNORE_IPS[] = {"100.64.", "100.97.", "100.109.", "100.116", "180.97.88.", "180.97.165.", "180.97.166.",
                "1.255.100.", "1.31.128.", "1.32.224.", "1.32.240.", "1.32.241.", "1.32.242.", "1.32.244.", "42.236.72.", "42.48.109.", "58.58.81.",
                "61.128.96.", "61.147.166.", "61.175.225.", "103.15.183.", "106.119.182.", "106.42.25.", "111.13.147.", "111.202.168.", "111.202.98.",
                "111.40.232.", "111.47.226.", "112.25.16.", "112.4.93.", "112.90.216.", "113.107.238.", "113.200.91.", "113.207.76.", "116.211.121.",
                "116.55.250.", "117.21.219.", "117.23.61.", "118.212.233.", "119.188.35.", "122.228.238.", "123.125.242.", "123.155.158.", "153.37.232.",
                "180.96.21.", "180.97.158.", "183.110.242.", "183.222.96.", "183.224.11.", "203.90.247.", "210.76.61.", "218.8.55.", "219.153.73.",
                "220.176.196.", "221.236.7.", "222.240.184.", "218.94.206.", "218.94.207.", "218.94.208.", "218.94.209.", "218.94.230.", "211.93.144.",
                "211.93.145.", "211.93.146.", "211.93.147.", "211.93.148.", "116.211.175.", "116.211.176.", "116.211.177.", "116.211.178.", "121.29.12.",
                "121.29.13.", "121.29.14.", "121.29.15."
        };

        public static boolean ignoreIps(String ip) {
            if(StringUtils.isBlank(ip) || !isIPv4LiteralAddress(ip)) {
                return true;
            }
            for (String ignoreIp : IGNORE_IPS) {
                if(ip.startsWith(ignoreIp)){
                    return true;
                }
            }
            return false;
        }

        private static boolean isIPv4LiteralAddress(String var0) {
            return textToNumericFormatV4(var0) != null;
        }

        private static byte[] textToNumericFormatV4(String var0) {
            byte[] var1 = new byte[4];
            long var2 = 0L;
            int var4 = 0;
            boolean var5 = true;
            int var6 = var0.length();
            if (var6 != 0 && var6 <= 15) {
                for(int var7 = 0; var7 < var6; ++var7) {
                    char var8 = var0.charAt(var7);
                    if (var8 == '.') {
                        if (var5 || var2 < 0L || var2 > 255L || var4 == 3) {
                            return null;
                        }

                        var1[var4++] = (byte)((int)(var2 & 255L));
                        var2 = 0L;
                        var5 = true;
                    } else {
                        int var9 = Character.digit(var8, 10);
                        if (var9 < 0) {
                            return null;
                        }

                        var2 *= 10L;
                        var2 += (long)var9;
                        var5 = false;
                    }
                }

                if (!var5 && var2 >= 0L && var2 < 1L << (4 - var4) * 8) {
                    switch(var4) {
                        case 0:
                            var1[0] = (byte)((int)(var2 >> 24 & 255L));
                        case 1:
                            var1[1] = (byte)((int)(var2 >> 16 & 255L));
                        case 2:
                            var1[2] = (byte)((int)(var2 >> 8 & 255L));
                        case 3:
                            var1[3] = (byte)((int)(var2 >> 0 & 255L));
                        default:
                            return var1;
                    }
                } else {
                    return null;
                }
            } else {
                return null;
            }
        }

        public static boolean isInnerIp(String ip) {
            ip = StringUtils.trimToEmpty(ip);
            byte[] addr = textToNumericFormatV4(ip);
            if(ArrayUtils.isEmpty(addr)){
                return false;
            }
            final byte b0 = addr[0];
            final byte b1 = addr[1];
            //10.x.x.x/8
            final byte SECTION_1 = 0x0A;
            //172.16.x.x/12
            final byte SECTION_2 = (byte) 0xAC;
            final byte SECTION_3 = (byte) 0x10;
            final byte SECTION_4 = (byte) 0x1F;
            //192.168.x.x/16
            final byte SECTION_5 = (byte) 0xC0;
            final byte SECTION_6 = (byte) 0xA8;
            switch (b0) {
                case SECTION_1:
                    return true;
                case SECTION_2:
                    if (b1 >= SECTION_3 && b1 <= SECTION_4) {
                        return true;
                    }
                case SECTION_5:
                    switch (b1) {
                        case SECTION_6:
                            return true;
                    }
                default:
                    return false;
            }
        }

        public IpUtils() {
        }

        public static long byte2Long(byte[] arr) {
            long ret = 0L;

            for (int i = 0; i < arr.length; ++i) {
                long t = 1L;

                for (int j = 0; j < i; ++j) {
                    t *= 256L;
                }

                ret += (long) (arr[i] < 0 ? 256 + arr[i] : arr[i]) * t;
            }

            return ret;
        }

        public static int byte2int(byte[] byteArray) {
            if (byteArray == null) {
                return 0;
            } else {
                int num = 0;

                for (int i = 0; i < 4; ++i) {
                    num += (byteArray[i] & 255) << 8 * (3 - i);
                }

                return num;
            }
        }

        public static byte[] int2Byte(int intValue) {
            byte[] b = new byte[4];

            for (int i = 0; i < 4; ++i) {
                b[i] = (byte) (intValue >> 8 * (3 - i) & 255);
            }

            return b;
        }

        public static String getIpStringFromInt(int ip) {
            return getIpStringFromBytes(int2Byte(ip));
        }

        public static String getIpStringFromBytes(byte[] ip) {
            StringBuffer sb = new StringBuffer();
            sb.append(ip[0] & 255);
            sb.append('.');
            sb.append(ip[1] & 255);
            sb.append('.');
            sb.append(ip[2] & 255);
            sb.append('.');
            sb.append(ip[3] & 255);
            return sb.toString();
        }

        public static int getIpIntFromString(String ip) {
            return byte2int(getIpByteArrayFromString(ip));
        }

        public static byte[] getIpByteArrayFromString(String ip) {
            byte[] ret = new byte[4];
            StringTokenizer st = new StringTokenizer(ip, ".");

            try {
                ret[0] = (byte) (Integer.parseInt(st.nextToken()) & 255);
                ret[1] = (byte) (Integer.parseInt(st.nextToken()) & 255);
                ret[2] = (byte) (Integer.parseInt(st.nextToken()) & 255);
                ret[3] = (byte) (Integer.parseInt(st.nextToken()) & 255);
                return ret;
            } catch (Exception var4) {
                return null;
            }
        }

        public static int getLocalInnerIp() {
            return innerIp;
        }

        public static int getLocalOuterIp() {
            return outerIp;
        }

        public static void main(String[] args) {
            System.out.println(getIpStringFromInt(getLocalInnerIp()));
            System.out.println(getIpStringFromInt(getLocalOuterIp()));
        }

        static {
            Enumeration netInterfaces = null;

            try {
                netInterfaces = NetworkInterface.getNetworkInterfaces();

                label46:
                while (netInterfaces.hasMoreElements()) {
                    NetworkInterface ni = (NetworkInterface) netInterfaces.nextElement();
                    Enumeration ips = ni.getInetAddresses();

                    while (true) {
                        while (true) {
                            String ip;
                            do {
                                if (!ips.hasMoreElements()) {
                                    continue label46;
                                }

                                ip = ((InetAddress) ips.nextElement()).getHostAddress();
                            } while (StringUtils.equals(ip, "127.0.0.1"));

                            if (!StringUtils.startsWith(ip, "192.168.") && !StringUtils.startsWith(ip, "10.") && !StringUtils.startsWith(ip, "172.")) {
                                outerIp = getIpIntFromString(ip);
                            } else {
                                innerIp = getIpIntFromString(ip);
                            }
                        }
                    }
                }
            } catch (Exception var4) {
                var4.printStackTrace();
            }

            if (innerIp == 0) {
                innerIp = getIpIntFromString("127.0.0.1");
            }

        }

    }
